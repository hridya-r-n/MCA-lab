import React from "react";

function StudentList(){
    const students=[
        {id:1,name:'Ann'},
        {id:2,name:'Tom'},
        {id:3,name:'Jerry'},
        {id:4,name:'John'},
    ];
    return(
        <div>
        <h1>Student Details</h1>
        <ul>
            {students.map(student=>(
                <li key={student.id}>ID:{student.id}   Name:{student.name}</li>
            ))}
        </ul>
        </div>
    )
}
export default StudentList;