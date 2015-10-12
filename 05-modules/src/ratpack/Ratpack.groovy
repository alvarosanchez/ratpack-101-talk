import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import ratpack.groovy.template.MarkupTemplateModule
import ratpack.registry.Registry

import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack

ratpack {
  bindings {
    bind BookRepository, DefaultBookRepository
    bind BookService, DefaultBookService

    module MarkupTemplateModule
  }

  handlers {

    register(Registry.single(new BookRenderer()))

    get {
      render "Hello JDD!"
    }

    get("welcome") {
      render groovyMarkupTemplate("index.gtpl", welcomeMessage: "Hello JDD!")
    }

    get("api/book/:isbn") { BookService bookService ->
      String isbn = pathTokens["isbn"]
      render bookService.getBook(isbn)
    }
  }
}
