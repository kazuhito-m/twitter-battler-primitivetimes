'use strict';

const Condition = require('./condition');

function getDefaultConditionValue() {
    const condition = new Condition();
    condition.productName = 'twitter-battler-primitivetimes';
    return condition.productName;
}

// ESLintが「未使用だ！」怒るので…しゃーなしで書く。
// (どうしてもかいくぐれないなら .eslintrc での対応を考える)
getDefaultConditionValue();