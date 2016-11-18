'use strict';

const Condition = require('./condition');

function getDefaultConditionValue() {
    const condition = new Condition();
    condition.productName = 'twitter-battler-primitivetimes';
    return condition.productName;
};
