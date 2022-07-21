import React from "react";
import './CustomTable.css'
import CustomButton from "../CustomButton/CustomButton";


const CustomTable = (
    {
        books,
        handleRemoveClick,
        handleEditClick

    }
) => {

    return (
        <table>
            <tbody>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Author</th>
                <th>Genre</th>
                <th>Actions</th>
            </tr>
            {
                books.map((book) => (
                    <tr key={book.id}>
                        <td>{book.id}</td>
                        {/*<td>{id}</td>*/}
                        <td>{book.name}</td>
                        <td>{book.author}</td>
                        <td>{book.genre}</td>
                        <td>
                            <div>

                                <CustomButton
                                    label={"Edit"}
                                    classNames={"edit-action"}
                                    handleClick={handleEditClick}
                                    data={({book})}
                                    type={"button"}
                                />
                                <CustomButton
                                    label={"Remove"}
                                    classNames={"delete-remove"}
                                    handleClick={handleRemoveClick}
                                    data={(book.id)}
                                    type={"button"}
                                />

                            </div>
                        </td>
                    </tr>
                ))
            }

            </tbody>
        </table>
    );
}


export default CustomTable;