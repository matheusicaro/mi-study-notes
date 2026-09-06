/**
 * Calculates the payment status based on day values.
 * @param dueDay The day the payment is due.
 * @param statementCloseDay The day the billing statement closes.
 * @param paymentDay The day the payment was made.
 * @param today The current day (unused in the specified logic).
 * @returns The payment status string.
 */
function ____isCreditCardPaid(...args: any[]): string {
  const dueDay = args[0] as number;
  const statementCloseDay = args[1] as number;
  const paymentDay = args[2] as number;
  const monthDay = args[3 as number];
  const isDebug = args[4] as boolean;

  const inputsDebug = `  ARGS: 
    dueDay( ${dueDay} ), 
    statementCloseDay( ${statementCloseDay} ), 
    paymentDay( ${paymentDay} ), 
    monthDay( ${monthDay} )
  `;

  if (isDebug) {
    return inputsDebug;
  }

  if (!dueDay || !statementCloseDay || !monthDay) {
    return `UNDEFINED_INPUTS > ${inputsDebug}`;
  }

  if (dueDay > statementCloseDay) {
    return "PAID!";
  }

  if (dueDay < statementCloseDay && dueDay > paymentDay) {
    return "NOT_PAID!";
  }

  return "UNDEFINED_STATUS";
}

export { ____isCreditCardPaid };



