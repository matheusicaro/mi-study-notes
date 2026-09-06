module.exports = {
    env: {
        browser: true,
        es6: true, // run prj >= es6
        jest: true, // global variables for jest
    },
    // rules for plugins em dev depedencies
    extends: [
        'react-app',
        'airbnb',
        'plugin:@typescript-eslint/recommended',
        'prettier/@typescript-eslint',
    ],
    globals: {
        Atomics: 'readonly',
        SharedArrayBuffer: 'readonly',
    },
    parserOptions: {
        ecmaFeatures: {
            jsx: true, // utilizing jsx
        },
        ecmaVersion: 2018,
        sourceType: 'module', // using import and export
    },
    plugins: ['react', 'import', 'jsx-a11y'],
    rules: {
        'react/jsx-filename-extension': [
            'error',
            {
                extensions: ['.tsx'],
            },
        ],
        'import/prefer-default-export': 'off',
        //disable required return for functions
        '@typescript-eslint/explicit-function-return-type': 'off',
        //disable public and privater for methods, react will define
        '@typescript-eslint/explicit-member-accessibility': 'off'
    },
    settings: {
        'import/parsers': {
            '@typescript-eslint/parser': ['.ts', '.tsx'],
        },
        'import/resolver': {
            typescript: {},
        },
    },
};