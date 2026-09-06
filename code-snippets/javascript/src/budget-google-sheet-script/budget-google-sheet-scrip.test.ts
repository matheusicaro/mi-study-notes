import { ____isCreditCardPaid } from "./budget-google-sheet-script";

describe("____isCreditCardPaid", () => {
  const DEFAULT_STATEMENT_CLOSE_DAY = 23;
  const DEFAULT_PAYMENT_DAY = 5;

  const BEFORE_STATEMENT_CLOSE = DEFAULT_STATEMENT_CLOSE_DAY - 1;
  const AFTER_STATEMENT_CLOSE = DEFAULT_STATEMENT_CLOSE_DAY + 1;

  const _ = (args: { from: number; to: number }) =>
    new Array(args.to).fill(0).map((_, index) => args.from + (index + 1));

  const inputs = [
    {
      from: 1,
      to: BEFORE_STATEMENT_CLOSE,
      expected: "PAID!",
    },
    {
      from: AFTER_STATEMENT_CLOSE,
      to: BEFORE_STATEMENT_CLOSE,
      expected: "NOT_PAID",
    },
  ];

  it.each(inputs)("should return $expected when due day is $dueDay and month day $monthDay", (input) => {
    const result = ____isCreditCardPaid(input.dueDay, DEFAULT_STATEMENT_CLOSE_DAY, DEFAULT_PAYMENT_DAY, input.monthDay);

    expect(result).toBe(input.expected);
  });
});
















