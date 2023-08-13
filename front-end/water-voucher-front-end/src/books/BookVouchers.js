import axios from "axios";
import React from "react";
import { useState } from "react";
import { useEffect } from "react";
import { useLocation } from "react-router";

function BookVouchers() {
  const search = useLocation().search;
  const [vouchers, setVouchers] = useState(null);
  const searchParams = new URLSearchParams(search);
  let bookId = searchParams.get("bookId");
  const title = (
    <td>
      <b>vouchers of book no: {bookId}</b>
    </td>
  );

  useEffect(() => {
    axios
      .get("http://localhost:8080/books/vouchers/bookId=" + bookId)
      .then((res) => {
        setVouchers(res.data);
      });
  }, [bookId]);

  return (
    <div>
      <table>
        <tbody>
          <tr>{title}</tr>
          {vouchers?.map((v) => (
            <tr key={v.voucherId}>
              <td>{v.voucherSerial}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default BookVouchers;
