/**
 * Each game should be declared as an Array with the numbers of the games
 * example: [ game_1, game_2, game_3 ]
 */
const games = [
  [1, 2, 3, 6, 8, 9, 10, 11, 13, 14, 16, 18, 21, 22, 25],
  [1, 3, 7, 8, 10, 11, 12, 14, 15, 16, 18, 19, 23, 24, 25],
  [1, 3, 4, 6, 8, 12, 15, 17, 18, 19, 20, 21, 22, 23, 24],
  [1, 4, 6, 8, 10, 13, 14, 15, 16, 17, 18, 19, 20, 21, 23],
];

/**
 * O resultado oficial da loteria
 */
const OFFICIAL_RESULT = [2, 3, 4, 8, 9, 10, 12, 14, 15, 16, 18, 21, 22, 23, 24];

games.map((game, indexOfGame) => {
  const match = [];
  const notMatch = [];

  game.map((value) => {
    const indexOfMatch = OFFICIAL_RESULT.indexOf(value);

    if (indexOfMatch >= 0) {
      match.push({ value, official: OFFICIAL_RESULT[indexOfMatch] });
    } else {
      notMatch.push({ value });
    }
  });

  const message = {
    game: indexOfGame + 1,
    total: match.length,
    match,
    notMatch,
  };

  const getMatchMessage = (m) => m.map((v) => (v.official ? `[${v.value}, ${v.official}]` : `${v.value}`));

  console.log(`
    game: ${message.game},
    total_match: **${message.total}**,
    match: ${getMatchMessage(message.match)},
    not_match: ${getMatchMessage(message.notMatch)},
    official: ${OFFICIAL_RESULT},
    `);
});
