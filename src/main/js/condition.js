'use strict';
/**
 * 検索条件VOクラス。
 * 送信値 -> X -> DB検索条件(where句) 
 * というような中間的な存在を担う。
 */
class Condition {

    get productName() {
        return this._productName;
    }

    set productName(value) {
        this._productName = value;
    }

    get miuraUse() {
        return this._miuraUse;
    }

    set miuraUse(value) {
        if (value == null || typeof value === 'boolean') {
            this._miuraUse = value;
        } else {
            throw new TypeError('プロパティ : miuraUse は boolean 型以外はsetできません。');
        }
    }

}

module.exports = Condition;