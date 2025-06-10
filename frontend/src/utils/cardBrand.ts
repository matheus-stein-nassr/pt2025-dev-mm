export function getCardBrand(number: string): "visa" | "mastercard" | null {
  const onlyNums = number.replace(/\D/g, "");

  if (onlyNums.startsWith("4")) return "visa";

  const twoDigits = onlyNums.substring(0, 2);
  if (/^5[1-5]$/.test(twoDigits)) return "mastercard";

  if (onlyNums.length >= 4) {
    const prefix = parseInt(onlyNums.substring(0, 4), 10);
    if (prefix >= 2221 && prefix <= 2720) return "mastercard";
  }

  return null;
}
