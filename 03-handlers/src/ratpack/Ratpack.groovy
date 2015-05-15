import org.example.ratpack.NowHandler

import static ratpack.groovy.Groovy.ratpack

ratpack {
  handlers {

    handler {
      println request.uri
      next()
    }

    handler('foo') {
      render 'bar\n'
    }

    prefix('api') {

      handler('methods') {

        byMethod {

          get {
            render "GET /api/methods\n"
          }

          post {
            render "POST /api/methods\n"
          }

        }
      }

      handler('now', new NowHandler())

      handler(':username') {

        render "Hello, $pathTokens.username\n"

      }

    }



  }
}
