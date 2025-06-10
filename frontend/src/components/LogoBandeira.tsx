import React from "react";
import styled from "styled-components";
import visaLogo from "../assets/visa.png";
import masterLogo from "../assets/mastercard.png";

interface Props {
  brand: "visa" | "mastercard" | null;
}

const Logo = styled.img`
  height: 30px;
  width: auto;
  margin-top: 0px;
  margin-bottom: 5px;
`;

const LogoBandeira: React.FC<Props> = ({ brand }) => {
  if (brand === "visa") return <Logo src={visaLogo} alt="Visa" />;
  if (brand === "mastercard") return <Logo src={masterLogo} alt="MasterCard" />;
  return null;
};

export default LogoBandeira;
