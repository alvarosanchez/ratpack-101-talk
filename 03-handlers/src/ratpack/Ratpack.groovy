import org.example.ratpack.NowHandler

import static ratpack.groovy.Groovy.ratpack

ratpack {
  handlers {

    all {
      println request.uri
      next()
    }

    get('foo') {
      render 'bar\n'
    }

    prefix('api') {

      path('methods') {

        byMethod {

          get {
            render "GET /api/methods\n"
          }

          post {
            render "POST /api/methods\n"
          }

        }
      }

      path('now', new NowHandler())

      path(':username') {
        render "Hello, $pathTokens.username\n"
      }

    }

  }
}
