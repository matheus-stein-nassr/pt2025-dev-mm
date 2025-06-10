import React from "react";
import styled from "styled-components";

interface InputProps extends React.InputHTMLAttributes<HTMLInputElement> {
  label: string;
  error?: string;
}

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  margin-bottom: 1rem;
`;

const Label = styled.label`
  font-weight: 600;
  font-size: 0.9rem;
  margin-bottom: 0.25rem;
  color: #333;
`;

const Field = styled.input<{ $hasError: boolean }>`
  padding: 0.75rem 1rem;
  font-size: 1rem;
  border-radius: 8px;
  background-color: ${({ $hasError }) => ($hasError ? "#fff5f5" : "#f9f9f9")};
  border: 2px solid ${({ $hasError }) => ($hasError ? "#e63946" : "#ccc")};
  color: #222;
  transition: border-color 0.3s ease, box-shadow 0.3s ease;

  &:focus {
    border-color: ${({ $hasError }) => ($hasError ? "#e63946" : "#0077cc")};
    box-shadow: 0 0 0 2px
      ${({ $hasError }) =>
        $hasError ? "rgba(230, 57, 70, 0.3)" : "rgba(0, 119, 204, 0.3)"};
    outline: none;
  }
`;

const Error = styled.span`
  font-size: 0.75rem;
  color: #e63946;
  margin-top: 0.25rem;
`;

const Input = React.forwardRef<HTMLInputElement, InputProps>(
  ({ label, error, id, ...rest }, ref) => {
    const inputId = id || label.toLowerCase().replace(/\s+/g, "-");

    return (
      <Wrapper>
        <Label htmlFor={inputId}>{label}</Label>
        <Field id={inputId} {...rest} ref={ref} $hasError={!!error} />
        {error && <Error>{error}</Error>}
      </Wrapper>
    );
  }
);

export default Input;
