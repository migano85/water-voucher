import { BrowserRouter as Router, Route, Switch } from "react-router-dom"; //this import need to be like this and in the same order
import "./App.css";
import CustomersSearch from "./customers/CustomersSearch";
import AddCustomer from "./customers/AddCustomer";
import NotFound from "./NotFound";

function App() {
  return (
     <Router>
     <div className="App">
       <Switch>
         <Route exact path={"/"}>
           <CustomersSearch />
         </Route>
         <Route path={"/addCustomer"}>
           <AddCustomer />
         </Route>
         <Route path="*">
           <NotFound />
         </Route>
       </Switch>
     </div>
   </Router>
  );
}

export default App;
