import org.example.ratpack.User
import org.example.ratpack.UsernameHandler

import static ratpack.registry.Registry.single

import static ratpack.groovy.Groovy.ratpack

ratpack {
  handlers {

    prefix('api/:username') {

      all {
        String username = pathTokens.username
        User user = new User(username: username, age: new Random().nextInt(100))
        next(single(user))
      }

      path("age", new UsernameHandler())

      all { User user ->
        response.send user.username
      }


    }



  }
}
