import { BrowserRouter as Router, Route, Switch } from "react-router-dom"; //this import need to be like this and in the same order
import "./App.css";
import CustomersSearch from "./customers/CustomersSearch";
import AddCustomer from "./customers/AddCustomer";
import NotFound from "./NotFound";
import AddBook from "./books/AddBook";
import BookSearch from "./books/BooksSearch";
import HomePage from "./HomePage";
import EditCustomer from "./customers/EditCustomer";
import EditBook from "./books/EditBook";
import BottlesSearch from "./bottles/BottlesSearch";
import AddBottle from "./bottles/AddBottle";

function App() {
  return (
    <Router>
      <div className="App">
        <Switch>
          <Route exact path={"/"}>
            <HomePage />
          </Route>
          <Route path={"/customersSearch"}>
            <CustomersSearch />
          </Route>
          <Route path={"/addCustomer"}>
            <AddCustomer />
          </Route>
          <Route path={"/customers/:customerId"}>
            <EditCustomer />
          </Route>
          <Route path={"/addBook"}>
            <AddBook />
          </Route>
          <Route path={"/booksSearch"}>
            <BookSearch />
          </Route>
          <Route path={"/books/:currentBookId"}>
            <EditBook />
          </Route>
          <Route path={"/bottlesSearch"}>
            <BottlesSearch />
          </Route>
          <Route path={"/addBottle"}>
            <AddBottle />
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
