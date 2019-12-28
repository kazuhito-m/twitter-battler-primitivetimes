 MEMO
 ===

## server/front同居

- うらがみさんの資料
  - https://backpaper0.github.io/ghosts/spring-boot-doma-vue/#61

## Gradleからのプロジェクト同居設定まわり

- 本家の部分(Project#evaluationDependsOn(String))なんだけど…いまいちわからない
  - https://docs.gradle.org/current/javadoc/org/gradle/api/Project.html#evaluationDependsOn%28java.lang.String%29

## Nuxt.js+TSまわり

- これをそのまま実践
  - https://qiita.com/itouuuuuuuuu/items/51fc4f3edbfcd71548a5
- 公式…なんだけど、これ最新化されてるんだろうか？
  - https://typescript.nuxtjs.org/guide/setup.html

## Nuxt & TwitterLogin

- コレもそのまま実践
  - https://blog.8tak4.com/post/176366654280/impl-oauth-nuxtjs
  
## axios + cookie(サーバのセッション共有)

- これしてみたのだけど…CORSエラーが出るように
  - https://ondwn.com/blog-20190516/
  
## axios

- パラメタ指定方
  - https://qiita.com/taroc/items/f22f7dd5d6d5443c72a4
- カスタムヘッダーなど
  - https://www.yoheim.net/blog.php?q=20170801

## git

- 一時的に追跡を止める
  - https://www.it-swarm.net/ja/git/git%E3%81%8B%E3%82%89%E3%83%95%E3%82%A1%E3%82%A4%E3%83%AB%E3%82%92%E4%B8%80%E6%99%82%E7%9A%84%E3%81%AB%E8%BF%BD%E8%B7%A1%E3%81%97%E3%81%AA%E3%81%84/940615909/
  
## nuxt + axios + proxy

- コレ系設定したけど、ぜんっぜんダメなんだけど！
  - https://www.it-swarm.net/ja/git/git%E3%81%8B%E3%82%89%E3%83%95%E3%82%A1%E3%82%A4%E3%83%AB%E3%82%92%E4%B8%80%E6%99%82%E7%9A%84%E3%81%AB%E8%BF%BD%E8%B7%A1%E3%81%97%E3%81%AA%E3%81%84/940615909/
  - https://ja.nuxtjs.org/faq/http-proxy/
  - https://www.npmjs.com/package/@nuxtjs/proxy
  - https://public-constructor.com/nuxt-axios-proxy-module/
  
## spring mvc + cors

- 緩和の二択
  - https://public-constructor.com/nuxt-axios-proxy-module/
  - https://qiita.com/syukai/items/4e88749d1d62f1148e45
  
## Authorization Bearer ヘッ
ダまわり

- 読み物
  - https://www.getto.systems/entry/2017/10/19/004734

## Twitterユーザ認証まわり

- Pythonの例だけど
  - https://qiita.com/mikan3rd/items/686e4978f9e1111628e9
- ブラウザから直接TwitterのAPIを叩くことはできない
  - https://scrapbox.io/kadoyau/%E3%83%96%E3%83%A9%E3%82%A6%E3%82%B6%E3%81%8B%E3%82%89%E7%9B%B4%E6%8E%A5Twitter%E3%81%AEAPI%E3%82%92%E5%8F%A9%E3%81%8F%E3%81%93%E3%81%A8%E3%81%AF%E3%81%A7%E3%81%8D%E3%81%AA%E3%81%84
- ブラウザから試したが、勿論出来ない
  - https://www.npmjs.com/package/oauth
- 認証の種類について
  - https://i-yusuke.com/entry/twitter-bearer-token/
- 動かない…けれど勉強にはなった
  - https://qiita.com/olt/items/89e4a5846ea8db8866dc
- これもTwitterじゃない例
  - https://qiita.com/ryu3/items/4ddc0364b3eb8d5dd402

## Twitter自体の操作

- ここを丸パクリした…Starterは使えるようになるんだろうか
  - https://sivalabs.in/2016/04/creating-custom-springboot-starter-for/
- アプリ開発は、ちょっとまえから厳しくなっている
  - https://qiita.com/kngsym2018/items/2524d21455aac111cdee

## nuxt関連

- QueryParameterのとり方
  - https://stackoverflow.com/questions/44748575/how-to-get-current-route-name-in-nuxt-js
- 好きなフォントを使う
  - https://qiita.com/yujiteshima/items/e4a2cf1574ad6e61feda
  
## nuxt oAuth

予め言うておくと「Tiwtter認証はくらいあんとではできない」ので、恐らサーバを挟む例が多い。

- Twitterだけ仲間はずれ、しゅってできそうで、ゆめあ在ったのに
  - https://github.com/nuxt-community/auth-module/blob/dev/examples/demo/pages/login.vue
- これもExpress使ってる例
  - https://qiita.com/devalon/items/4aee442693862667c1eb
  