import './App.css';
import React, {useState} from "react";
import CustomInput from "../CustomInput/CustomInput";
import CustomButton from "../CustomButton/CustomButton";
import CustomTable from "../CustomTable/CustomTable";
import axios from "axios";


const initialValues = {
    id: '',
    name: '',
    author: '',
    genre: '',
    bookComments: []

}

const baseUrl = 'http://localhost:8081'

const config = {
    headers: {
        'Content-Type': 'application/json'
    }
};

function App() {

    const [bookData, setBookData] = useState(initialValues);

    const [books, setBooks] = useState(findAllBook())
    const [editableBookData, setEditableBookData] = useState({
        isEdit: false,
        bookId: null
    })

    function findAllBook() {
        let booksData = []
        axios.get(baseUrl + '/api/v1/books', config)
            .then(response => {
                response.data
                    .map((book) => responseMapToBookData(book))
                    .forEach((book) => booksData.push(book))
            });
        return booksData;
    }


    const isFilledFields = bookData.name && bookData.author && bookData.genre


    const responseMapToBookData = (book)=> {
        return {
            id: book.id,
            name: book.name,
            author: book.author,
            genre: book.genre
        }
    }

    const handleSubmitBook = (event) => {
        event.preventDefault();
        if (isFilledFields) {
            if (editableBookData.isEdit) {
                const editedBooks = books;
                const updateBookData = axios.put(baseUrl + '/api/v1/books', {
                    id: bookData.id,
                    name: bookData.name,
                    author: bookData.author,
                    genre: bookData.genre,
                    bookComments: bookData.bookComments
                }, config).then(response => {
                    console.log(updateBookData);
                    editedBooks.splice(editableBookData.bookId, 1, responseMapToBookData(response.data));
                    setBooks(editedBooks);
                    setEditableBookData({
                        isEdit: false,
                        bookId: null
                    });
                })
            } else {

                axios.post(baseUrl+'/api/v1/books', {
                    name: bookData.name,
                    author: bookData.author,
                    genre: bookData.genre,
                    bookComments: bookData.bookComments
                }, config).then(response => {
                    setBooks(prevState => [...prevState, responseMapToBookData(response.data)]);
                })


            }
            setBookData(initialValues)
        }
    };

    const handleCleanClick = () => setBookData(initialValues);

    const handleRemoveClick = (id) => {
        axios.delete(baseUrl+'/api/v1/books/'+id)
        setBooks(books.filter((book) => book.id !== id));
    }

    const handleEditClick = ({book}) => {
        setBookData(book)
        setEditableBookData({
            isEdit: true,
            bookId: book.id
        });
    };

    const handleInputChange = (event, fieldName) => {
        setBookData(prevState => ({
            ...prevState,
            [fieldName]: event.target.value
        }))
    }


    return (
        <div className={"wrapper"}>
            <div className={"wrapper-content"}>
                <div className={"table-data"}>
                    <CustomTable
                        books={books}
                        handleRemoveClick={handleRemoveClick}
                        handleEditClick={handleEditClick}
                    />
                </div>

                <div>
                    <form onSubmit={handleSubmitBook} onReset={handleCleanClick}>
                        <CustomInput
                            placeholder={"Write Book Name"}
                            handleChange={handleInputChange}
                            value={bookData.name}
                            fieldName={"name"}
                        />
                        <CustomInput
                            placeholder={"Write Book Author"}
                            handleChange={handleInputChange}
                            value={bookData.author}
                            fieldName={"author"}
                        />
                        <CustomInput
                            placeholder={"Write Book Genre"}
                            handleChange={handleInputChange}
                            value={bookData.genre}
                            fieldName={"genre"}
                        />
                        <div className={"buttons-wrapper"}>
                            <CustomButton
                                label={"Clean"}
                                type={"reset"}
                            />
                            <CustomButton
                                label={editableBookData.isEdit ? 'Update' : 'Save'}
                                disabled={!isFilledFields}
                                type={"submit"}
                            />
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default App;
