import styled from "styled-components";

const Button = styled.button<{ disabled?: boolean }>`
  width: 100%;
  padding: 0.75rem;
  background-color: ${({ disabled }) => (disabled ? "#ccc" : "#008000")};
  color: white;
  font-size: 1rem;
  border: none;
  border-radius: 8px;
  cursor: ${({ disabled }) => (disabled ? "not-allowed" : "pointer")};
  transition: background 0.3s;

  &:hover {
    background-color: ${({ disabled }) => (disabled ? "#ccc" : "#228b22")};
  }
`;

export default Button;
