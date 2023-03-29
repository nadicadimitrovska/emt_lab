import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Redirect, Route} from "react-router-dom";
import Authors from "../Authors/authors";
import LibraryService from "../../repository/libraryRepository";
import Books from "../Books/BookList/books";
import Countries from "../Countries/countries";
import Header from "../Header/header";
import BookAdd from "../Books/BookAdd/bookAdd";
import BookEdit from "../Books/BookEdit/bookEdit";
import Category from "../Category/category";

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            authors: [],
            books: [],
            categories: [],
            countries: [],
            selectedBook: {}
        }
    }

    render() {
        return (
          <Router>
              <Header/>
              <main>
                  <div className="container">
                      <Route path={"/authors"} exact render={() => <Authors authors={this.state.authors}/>}/>
                      <Route path={"/books/add"} exact render={() => <BookAdd books={this.state.books} categories={this.state.categories} authors={this.state.authors} onAddBook={this.addBook}/>}/>
                      <Route path={"/books/edit/:id"} exact render={() => <BookEdit books={this.state.books} categories={this.state.categories} authors={this.state.authors} onEditBook={this.editBook} book={this.state.selectedBook}/>}/>
                      <Route path={"/books"} exact render={() => <Books books={this.state.books} onDelete={this.deleteBook}  onEdit={this.getBook} onMark={this.markBook}/>}/>
                      <Route path={"/countries"} exact render={() => <Countries countries={this.state.countries}/>}/>
                      <Route path={"/categories"} exact render={() => <Category categories={this.state.categories}/>}/>
                      <Route path={"/"}  book={<Redirect replace to="/books"/>}/>
                      <Redirect to={"/books"}/>
                  </div>
              </main>
          </Router>
        );
    }

    loadAuthors = () => {
        LibraryService.fetchAuthors()
            .then((data)=>{
               this.setState({
                   authors: data.data
               })
            });
    }

    loadBooks = () => {
        LibraryService.fetchBooks()
            .then((data) =>{
                this.setState({
                    books: data.data
                })
            });
    }

    loadCountries = () => {
        LibraryService.fetchCountries()
            .then((data) =>{
                this.setState({
                    countries: data.data
                })
            });
    }

    loadCategories = () => {
        LibraryService.fetchCategories()
            .then((data) =>{
                this.setState({
                    categories: data.data
                })
            });
    }

    deleteBook = (id) => {
        LibraryService.deleteBook(id)
            .then(()=>{
                this.loadBooks();
            });
    }

    addBook = (name, category, author, availableCopies) =>{
        LibraryService.addBook(name, category, author, availableCopies)
            .then(()=>{
               this.loadBooks();
            });
    }

    getBook = (id) => {
        LibraryService.getBook(id)
            .then((data)=>{
                this.setState({
                    selectedBook: data.data
                })
            });
    }

    editBook = (id, name, category, author, availableCopies) => {
        LibraryService.editBook(id, name, category, author, availableCopies)
            .then(()=>{
                this.loadBooks();
            });
    }

    markBook = (id) => {
        LibraryService.markBook(id)
            .then(()=>{
                this.loadBooks();
            });
    }

    componentDidMount() {
        this.loadAuthors();
        this.loadCountries();
        this.loadBooks();
        this.loadCategories();
    }
}

export default App;
