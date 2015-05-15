import ratpack.groovy.template.MarkupTemplateModule
import ratpack.jackson.JacksonModule

import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack

ratpack {
  bindings {
    bind BookRepository, DefaultBookRepository
    bind BookService, DefaultBookService
    bind BookRenderer
    add MarkupTemplateModule
    add JacksonModule
  }

  handlers {
    get {
      render "Hello GeeCON!"
    }

    get("welcome") {
      render groovyMarkupTemplate("index.gtpl", welcomeMessage: "Hello GeeCON!")
    }

    get("api/book/:isbn") { BookService bookService ->
      String isbn = pathTokens["isbn"]
      render bookService.getBook(isbn)
    }
  }
}
