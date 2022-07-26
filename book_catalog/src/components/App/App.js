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


const initUserData = {
    userName: '',
    userPassword: '',
    submitType: ''
}

const baseUrl = 'http://localhost:8081'

const config = {
    headers: {
        'Content-Type': 'application/json'
    }
};

// axios.defaults.withCredentials = true
// axios.defaults.xsrfHeaderName = "X-CSRFToken"



function App() {

    const [bookData, setBookData] = useState(initialValues);
    const [securityCookiesData, setSecurityCookiesData] = useState({
        authentication: false
    })

    const [books, setBooks] = useState(findAllBook())
    const [editableBookData, setEditableBookData] = useState({
        isEdit: false,
        bookId: null
    })


    const [userData, setUserData] = useState(initUserData)

    function findAllBook() {
        let booksData = []
        if (securityCookiesData.authentication) {
            axios.get(baseUrl + '/api/v1/books', config)
                .then(response => {
                    response.data
                        .map((book) => responseMapToBookData(book))
                        .forEach((book) => booksData.push(book))
                });
        }

        return booksData;
    }


    const isFilledBookDataFields = bookData.name && bookData.author && bookData.genre
    const isFilledUserDataFields = userData.userName && userData.userPassword


    const responseMapToBookData = (book) => {
        return {
            id: book.id,
            name: book.name,
            author: book.author,
            genre: book.genre
        }
    }

    const handleSubmitBook = (event) => {
        event.preventDefault();
        console.log(event.label)
        if (isFilledBookDataFields) {
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



    const handleSubmitUser = (event) => {
        event.preventDefault();
        console.log(userData.userName)
        console.log(userData.userPassword)

        if (userData.submitType === 'Create') {
            console.log("isCreate")
            axios.post(baseUrl + '/api/v1/users', {
                username:  userData.userName,
                password: userData.userPassword
            }, config).then(response => {
                console.log("response: "+ response)
                setUserData(initUserData)
            })
        } else if (userData.submitType === 'Login') {
            console.log("isLogin");
            axios.post(baseUrl + '/login', {
                username:  userData.userName,
                password: userData.userPassword
            }, config).then(response => {
                console.log(response)
                console.log(response.headers)
                // if (response.status === 200) {
                //     setSecurityCookiesData({
                //         authentication: true
                //     })
                // }
            })
        } else {
            console.log("Не известный submitType: " + userData)
        }
    }

    const handleCleanClick = () => setBookData(initialValues);

    const handleRemoveClick = (id) => {
        axios.delete(baseUrl + '/api/v1/books/' + id)
        setBooks(books.filter((book) => book.id !== id));
    }

    const handleEditClick = ({book}) => {
        setBookData(book)
        setEditableBookData({
            isEdit: true,
            bookId: book.id
        });
    };

    const handleInputBookChange = (event, fieldName) => {
        setBookData(prevState => ({
            ...prevState,
            [fieldName]: event.target.value
        }))
    }

    const handleInputUserChange = (event, fieldName) => {
        setUserData(prevState => ({
            ...prevState,
            [fieldName]: event.target.value
        }))
    }


    const handleClickCreateUser = (userData) => {
        setUserData(prevState => ({
            ...prevState,
            submitType: 'Create'
        }))
    }
    const handleClickLoginUser = (userData) => {
        setUserData(prevState => ({
            ...prevState,
            submitType: 'Login'
        }))
    }

    return (
        <div className={"wrapper"}>
            {!securityCookiesData.authentication ?

                <div className={"wrapper-content"}>
                    <div>
                        <form onSubmit={handleSubmitUser} onReset={handleCleanClick}>
                            <CustomInput
                                placeholder={"Write User Name"}
                                handleChange={handleInputUserChange}
                                value={userData.userName}
                                fieldName={"userName"}
                            />
                            <CustomInput
                                placeholder={"Write User Password"}
                                handleChange={handleInputUserChange}
                                value={userData.userPassword}
                                fieldName={"userPassword"}
                            />
                            <div className={"buttons-wrapper"}>
                                <CustomButton
                                    label={"Create"}
                                    disabled={!isFilledUserDataFields}
                                    handleClick={handleClickCreateUser}
                                    data={userData}
                                    type={"submit"}
                                />
                                <CustomButton
                                    label={"Login"}
                                    disabled={!isFilledUserDataFields}
                                    handleClick={handleClickLoginUser}
                                    data={userData}
                                    type={"submit"}
                                />
                                <CustomButton
                                    label={"Clean"}
                                    type={"reset"}
                                />
                            </div>
                        </form>
                    </div>
                </div>

                :
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
                                handleChange={handleInputBookChange}
                                value={bookData.name}
                                fieldName={"name"}
                            />
                            <CustomInput
                                placeholder={"Write Book Author"}
                                handleChange={handleInputBookChange}
                                value={bookData.author}
                                fieldName={"author"}
                            />
                            <CustomInput
                                placeholder={"Write Book Genre"}
                                handleChange={handleInputBookChange}
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
                                    disabled={!isFilledBookDataFields}
                                    type={"submit"}
                                />
                            </div>
                        </form>
                    </div>
                </div>
            }
        </div>
    );
}

export default App;
