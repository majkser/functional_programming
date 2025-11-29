package app
object MinimalApplication extends cask.MainRoutes:

  override def host = "0.0.0.0"
  override def port = 8080

  initialize()
