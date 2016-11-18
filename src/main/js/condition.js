'use strict';
/**
 * 検索条件VOクラス。
 * 送信値 -> X -> DB検索条件(where句) 
 * というような中間的な存在を担う。
 */
function Condition() {

    const values = {}; // 内部プロパティ変数を貯める場所。

    // FIXME プロパティを実現するのに…こんなに複雑なことしなくても本当は行けるはず…。

    function makeAccesserProductName(values) {
        return {
            enumerable: true,
            set: (value) => {
                values.productName = value;
            },
            get: () => {
                return values.productName;
            }
        };
    }

    function makeAccesserMiuraUse(values) {
        return {
            enumerable: true,
            set: (value) => {
                if (value == null || typeof value === 'boolean') {
                    values.miuraUse = value;
                } else {
                    throw new TypeError('プロパティ : miuraUse は boolean 型以外はsetできません。');
                }
            },
            get: () => {
                return values.miuraUse;
            }
        };
    }

    Object.defineProperty(this, 'productName', makeAccesserProductName(values));
    Object.defineProperty(this, 'miuraUse', makeAccesserMiuraUse(values));

}

module.exports = Condition;