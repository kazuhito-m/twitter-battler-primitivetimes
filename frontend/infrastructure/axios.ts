import axios from 'axios'

/**
 * 自動でProxyが効いてくれないので、ワークアラウンドとして設定済を返す感じに。
 */
const client = axios.create({
  baseURL: 'http://localhost:8080'
})

export default client
