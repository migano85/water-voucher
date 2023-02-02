import { useState, useEffect } from "react";
//custom hooks should start with the prefix (use), otherwise it won't work
const useFetch = (url) => {
  const [data, setData] = useState(null);
  const [isPending, setIsPending] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const abortCont = new AbortController();
    fetch(url, { signal: abortCont.signal })
      .then((res) => {
        if (!res.ok) {
          // error coming back from server
          throw Error("could not fetch the data for that resource");
        }
        return res.json();
      })
      .then((data) => {
        setIsPending(false);
        setData(data);
        setError(null);
      })
      .catch((err) => {
        // auto catches network / connection error
        if (err !== "AbortError") {
          //if abortCont.abort() is executed it will return an error, which means that the component is unmounted, in the that case we do not want to update the state of IsPending and error
          setIsPending(false);
          setError(err.message);
        }
      });
    return () => {
      console.log(
        "we are moving away from the page that uses this hook (i.e useFetch), only now this block which is associated with return will be executed "
      );
      abortCont.abort(); //abort any fetch still in progress
    }; /* this islike destructor, 
    when you make a callback function on return it means call this before the hook finishes and return. which happens, when you moove away from the component that called this hook (i.e. useFetch), in this case when you move away from HomePage.js or profileDetails.js execute whatever function asscoaited with RETURN, in this case it is an arrow function                  
    --------------------------------
    this abort the current fetch of data to prevent updating the state of constants inside UNmounted component (e.g. HomePage.js, profileDetails.js).
Note: 
1- AbortController and fetch() are part of web APIs not part of react
for more info and example 
https://developer.mozilla.org/en-US/docs/Web/API/AbortController
https://developer.mozilla.org/en-US/docs/Web/API/AbortSignal
https://developer.mozilla.org/en-US/docs/Web/API/fetch 

2- Web APIs are typically used with JavaScript, although this doesn't always have to be the case, for more info https://developer.mozilla.org/en-US/docs/Web/API
    */
  }, [url]);

  return { resData : data, isPending, error };//the name here should be used also in caller, in this case resData, the caller also should use the name resData like this return statement
};

export default useFetch;
