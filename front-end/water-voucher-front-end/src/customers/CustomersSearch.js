import useFetch from "../useFetch";
import CustomersSearchResult from "./CustomersSearchResult";

const CustomersSearch = () => {
  const {
    error,
    isPending,
    data: customers,
  } = useFetch("http://localhost:8081/customers");
  return (
    <>
      <h2 className="title">Customers</h2>
      {error && <div>{error}</div>}
      {isPending && <div>Loading...</div>}
      {customers && <CustomersSearchResult customers={customers} />}
    </>
  );
};

export default CustomersSearch;
