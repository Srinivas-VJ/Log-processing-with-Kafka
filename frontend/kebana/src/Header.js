import React from "react";

export const Header = ({ logo, title }) => {
  return (
    <header className="header" style={{ height: "10%", alignContent: "left" }}>
      <img src={logo} alt="Logo" className="header__logo" />
      <h1 className="header__title">{title}</h1>
    </header>
  );
};
