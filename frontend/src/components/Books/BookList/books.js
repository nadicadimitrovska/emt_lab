import React from "react";
import BookTerm from "../BookTerm/bookTerm";
import ReactPaginate from "react-paginate";
import {Link} from "react-router-dom";
import bookAdd from "../BookAdd/bookAdd";
import bookTerm from "../BookTerm/bookTerm";
//
// const books = (props) => {
//     return (
//         <div className={"container mm-4 mt-5"}>
//             <div className={"row"}>
//                 <div className={"row"}>
//                     <table className={"table table-striped"}>
//                         <thead>
//                         <tr>
//                             <th scope={"col"}>Name</th>
//                             <th scope={"col"}>Category</th>
//                             <th scope={"col"}>Author</th>
//                             <th scope={"col"}>Available Copies</th>
//                         </tr>
//                         </thead>
//                         <tbody>
//                         {props.books.map((term) =>{
//                             return (
//                                <BookTerm term={term} onDelete={props.onDelete} onEdit={props.onEdit} onMark={props.onMark}/>
//                             );
//                         })}
//                         </tbody>
//                     </table>
//                 </div>
//             </div>
//             <div className="col mb-3">
//                 <div className="row">
//                     <div className="col-sm-12 col-md-12">
//                         <Link className={"btn btn-block btn-dark"} to={"/books/add"}>Add new book</Link>
//                     </div>
//                 </div>
//             </div>
//         </div>
//     );
// }

// export default books;

class Books extends React.Component{
    constructor(props) {
        super(props);

        this.state={
            page: 0,
            size: 5
        }
    }

    render() {
        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.props.books.length / this.state.size);
        const books =this.getProductsPage(offset,nextPageOffset);

        return (
            <div className={"container mm-4 mt-5"}>
                <div className={"row"}>
                    <div className={"row"}>
                        <table className={"table table-striped"}>
                            <thead>
                            <tr>
                                <th scope={"col"}>Name</th>
                                <th scope={"col"}>Category</th>
                                <th scope={"col"}>Author</th>
                                <th scope={"col"}>Available Copies</th>
                            </tr>
                            </thead>
                            <tbody>
                            {books}
                            </tbody>
                        </table>
                    </div>

                    <div className="col mb-3">
                        <div className="row">
                            <div className="col-sm-12 col-md-12">
                                <Link className={"btn btn-block btn-dark"} to={"/books/add"}>Add new book</Link>
                            </div>
                        </div>
                    </div>
                </div>
                <ReactPaginate previousLabel={"back"}
                               nextLabel={"next"}
                               breakLabel={<a href="/#">...</a>}
                               breakClassName={"break-me"}
                               pageClassName={"ml-1"}
                               pageCount={pageCount}
                               marginPagesDisplayed={2}
                               pageRangeDisplayed={5}
                               onPageChange={this.handlePageClick}
                               containerClassName={"pagination m-4 justify-content-center"}
                               activeClassName={"active"}
                />
            </div>
        )
    }

    handlePageClick = (data) => {
        let selected = data.selected;
        this.setState({
            page: selected
        })
    }

    getProductsPage = (offset, nextPageOffset) => {
        return this.props.books.map((term, index) => {
            return (
                <BookTerm term={term} onDelete={this.props.onDelete} onEdit={this.props.onEdit} onMark={this.props.onMark}/>
            );
        }).filter((product, index) => {
            return index >= offset && index < nextPageOffset;
        })
    }

}

export default Books;