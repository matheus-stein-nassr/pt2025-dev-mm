// Remove qualquer caractere que não seja número
const onlyNumbers = (value: string): string => value.replace(/\D/g, "");

// CPF: 000.000.000-00
export const maskCPF = (value: string): string => {
  const digits = onlyNumbers(value).slice(0, 11);
  return digits
    .replace(/(\d{3})(\d)/, "$1.$2")
    .replace(/(\d{3})(\d)/, "$1.$2")
    .replace(/(\d{3})(\d{1,2})$/, "$1-$2");
};

// Cartão de Crédito: 0000 0000 0000 0000
export const maskCardNumber = (value: string): string => {
  const digits = onlyNumbers(value).slice(0, 16);
  return digits.replace(/(\d{4})(?=\d)/g, "$1 ").trim();
};

// Validade: MM/AA
export const maskDate = (value: string): string => {
  const digits = onlyNumbers(value).slice(0, 4);
  if (digits.length <= 2) return digits;
  return digits.replace(/(\d{2})(\d{1,2})/, "$1/$2");
};

// CVV: até 3 dígitos
export const maskCVV = (value: string): string => {
  return onlyNumbers(value).slice(0, 3);
};
