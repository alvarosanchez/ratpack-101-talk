import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static ratpack.groovy.Groovy.ratpack

ratpack {

  final Logger LOGGER = LoggerFactory.getLogger(this.class)

  handlers {

    handler {

      LOGGER.info "[1] Beginning of handler"
      String message

      blocking {
        LOGGER.info "[3] About to start a blocking operation"
        Thread.sleep(1000)
        return "world"
      }.map { String s ->
        LOGGER.info "[4] Transformation function"
        return "Hello ${s}"
      }.then { String s ->
        LOGGER.info "[5] Partial result"
        message = s
      }

      blocking {
        LOGGER.info "[6] Second blocking operation"
        Thread.sleep(1000)
        return "GeeCON"
      }.then { String s ->
        LOGGER.info "[7] Final result"
        response.send("${message} from ${s}\n")
      }

      LOGGER.info "[2] End of handler"
    }

  }
}
