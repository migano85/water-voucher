import { Link } from "react-router-dom";
import Grid from "@mui/material/Grid";
import { Paper, styled } from "@mui/material";

const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === "dark" ? "#1A2027" : "#fff",
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: "center",
  color: theme.palette.text.secondary,
}));
const HomePage = () => {
  return (
    <Grid container spacing={13}>
      <Grid
        item
        className="gridAsCard"

        // sx={{
        //   // backgroundColor: (theme) =>
        //   //   theme.palette.mode === "dark" ? "#1A2027" : "#fff",
        // }}
      >
        <Item>
          <Link className="linkAsFancyText" to="/customersSearch">
            CUSTOMERS
          </Link>
        </Item>
      </Grid>
      <Grid item>
        <Item>
          <Link className="linkAsFancyText" to="/customersSearch">
            CUSTOMERS
          </Link>
        </Item>
      </Grid>
      <Grid item>
        <Item>
          <Link className="linkAsFancyText" to="/customersSearch">
            CUSTOMERS
          </Link>
        </Item>
      </Grid>
    </Grid>

    // <div>
    //   <Link className="linkAsButton" to="/customersSearch">
    //     CUSTOMERS
    //   </Link>
    //   <Link className="linkAsButton" to="/booksSearch">
    //     BOOKS
    //   </Link>
    //   <Link className="linkAsButton" to="/bottlesSearch">
    //     BOTTLES
    //   </Link>
    // </div>
  );
};

export default HomePage;
