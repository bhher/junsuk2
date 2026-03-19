import React from "react";

function Wrapper({ children }) {
  const style = {
    border: "2px solid black",
    paddding: "16px",
  };
  return <div style={style}>{children}</div>;
}

export default Wrapper;
