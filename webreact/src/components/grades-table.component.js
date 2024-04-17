import axios from "axios";
import { useState, useEffect } from "react";
import Header from "./header.component";

export default function GradesTable() {
    const user = JSON.parse(localStorage.getItem("user"));

    const [grades, setGrades] = useState(new Map());
    const [isLoaded, setIsLoaded] = useState(false);

    useEffect(() => {
        setIsLoaded(false);
        const gradesMap = new Map();
        user.person.group.courses.forEach(async (element, index) => {
            await fetchGrades(user.person.id, element.id)
                .then((resp) => {
                    gradesMap.set(element.name, resp);
                })
                .catch((error) => {
                    console.log(error);
                });
            setGrades(gradesMap);
            setIsLoaded(true);
        });
    }, []);

    return (
        <>
            <Header />
            <div className="table-container">
                <h2>{user.person.name + " " + user.person.surname}</h2>
            </div>
            <div className="table-container">
                <div className="table">
                    {isLoaded && <TableRows gradesMap={grades} />}
                    {/* <TableRow courseName={"bebra"} grades={[7]} /> */}
                    {/* <TableRow courseName={"mma"} grades={[4, 5, 4, 4, 6, 5]}/> */}
                </div>
            </div>
        </>
    );
}

function TableRows({ gradesMap }) {
    const children = [];
    const preparedInfoArr = [];
    gradesMap.forEach((value, key) => {
        const preparedInfo = {
            courseName: key,
            grades: [],
        };

        value.forEach((grade) => {
            preparedInfo.grades.push({
                gradeValue: grade.value,
                gradeDate: grade.date,
            });
        });

        preparedInfoArr.push(preparedInfo);
    });

    preparedInfoArr.forEach((element, index) => {
        children.push(
            <TableRow
                key={index}
                courseName={element.courseName}
                grades={element.grades}
            />
        );
    });

    return children;
}

function TableRow({ courseName, grades }) {
    return (
        <div className="table-row">
            <div className="course-name">course: {courseName}</div>
            <div className="average-grade">Average: {getAverage(grades)}</div>
            <div className="grades-container">
                <Grades grades={grades} />
            </div>
        </div>
    );

    function getAverage(grades) {
        let sum = 0;
        grades.forEach((element) => {
            sum += element.gradeValue;
        });
        let avg = sum / grades.length;
        return +(Math.round(avg + "e+2") + "e-2");
    }
}

function Grades({ grades }) {
    const children = [];
    grades.forEach((element, index) => {
        children.push(
            <Grade
                key={index}
                grade={element.gradeValue}
                date={element.gradeDate}
            />
        );
    });

    return children;
}

function Grade({ grade, date }) {
    const [isHovered, setIsHovered] = useState(false);

    return (
        <div
            className="grades-item"
            onMouseEnter={() => setIsHovered(true)}
            onMouseLeave={() => setIsHovered(false)}
        >
            {grade}
            {isHovered && <div className="grade-details">{date}</div>}
        </div>
    );
}

async function fetchGrades(studentId, courseId) {
    const apiUrl = `grades/${studentId}/${courseId}`;
    let grades;
    await axios.get(apiUrl).then((resp) => {
        grades = resp.data;
    });
    return grades;
}
