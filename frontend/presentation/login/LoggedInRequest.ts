export default class LoggedInRequest {
  public constructor(
    public readonly oAuthToken: string,
    public readonly oAuthVerifier: string
  ) {
    console.log(this.oAuthToken) // TODO ルールの見直し。
  }

  public isEmpty(): boolean {
    return !(this.oAuthToken && this.oAuthVerifier)
  }
}
