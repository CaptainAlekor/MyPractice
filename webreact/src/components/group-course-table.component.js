import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Header from "./header.component";

export default function GroupCourseTable() {
    const { id } = useParams();
    const [students, setStudents] = useState(new Map());
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const fetchData = async () => {
            const groupCourseApiUrl = `/groupCourses/${id}`;
            const groupCourseData = await axios.get(groupCourseApiUrl);
            const studentsApiUrl = `/students?groupId=${groupCourseData.data.group.id}`;
            const studentsData = await axios.get(studentsApiUrl);

            const studentsMap = new Map();
            const gradesPromises = studentsData.data.map((student) => {
                studentsMap.set(student, null);
                return axios.get(
                    `/grades/${student.id}/${groupCourseData.data.course.id}`
                );
            });

            const gradesData = await Promise.all(gradesPromises);
            gradesData.forEach((gradeData, index) => {
                const student = studentsData.data[index];
                studentsMap.set(student, gradeData.data);
            });

            setStudents(studentsMap);
            setIsLoading(false);
        };

        fetchData();
    }, [id]);

    if (isLoading) {
        return <div>Loading...</div>;
    }

    return (
        <>
            <Header />
            <GroupTableRows students={students} />
        </>
    );
}

function GroupTableRows({ students }) {
    const children = [];
    students.forEach((value, key) => {
        children.push(
            <GroupTableRow
                key={key.id}
                studentId={key.id}
                studentName={key.name}
                studentSurname={key.surname}
                studentGrades={value}
            />
        );
    });

    return children;
}

function GroupTableRow({
    studentId,
    studentName,
    studentSurname,
    studentGrades,
}) {
    return (
        <div className="group-table-row">
            <div className="group-table-row-student">
                {studentName + " " + studentSurname}
            </div>
            <div className="group-table-row-grades">
                <Grades studentId={studentId} studentGrades={studentGrades} />
            </div>
        </div>
    );
}

function Grades({ studentId, studentGrades }) {
    const children = [];
    studentGrades.forEach((value) => {
        children.push(<Grade key={value.id} grade={value} />);
    });
    children.push(<GradeTemplate key={0} studentId={studentId} />);

    return children;
}

function Grade({ grade }) {
    const [showMenu, setShowMenu] = useState(false);
    return (
        <>
            <div className="grades-item" onClick={() => setShowMenu(true)}>
                {grade.value}
            </div>
            {showMenu && <GradeMenu grade={grade} setShowMenu={setShowMenu} />}
        </>
    );
}

function GradeMenu({ grade, setShowMenu }) {
    const navigate = useNavigate();

    return (
        <div className="grade-menu">
            <h2>Value: {grade.value}</h2>
            <h2>Date: {grade.date}</h2>
            <div className="menu-close" onClick={() => setShowMenu(false)}>
                x
            </div>
            <div className="grade-delete" onClick={deleteGrade}>
                Delete grade
            </div>
            <div
                className="grade-update"
                onClick={async () => {
                    await updateGradeValue(
                        grade.id,
                        prompt("Enter new grade value")
                    );
                    navigate(0);
                }}
            >
                Update grade value
            </div>
        </div>
    );

    async function deleteGrade() {
        await axios.delete(`/grades/delete/${grade.id}`);
        navigate(0);
    }
}

function GradeTemplate({ studentId }) {
    const [showDialog, setShowDialog] = useState(false);

    return (
        <>
            <div className="grades-item" onClick={() => setShowDialog(true)}>
                +
            </div>
            {showDialog && (
                <NewGradeDialog
                    studentId={studentId}
                    setShowDialog={setShowDialog}
                />
            )}
        </>
    );
}

function NewGradeDialog({ studentId, setShowDialog }) {
    const { id } = useParams();
    const navigate = useNavigate();

    return (
        <div className="grade-menu">
            <div className="menu-close" onClick={() => setShowDialog(false)}>
                x
            </div>
            <form onSubmit={checkNewGradeForm}>
                <div className="grade-menu-input">
                    <label>Value: </label>
                    <input
                        name="gradeValue"
                        type="number"
                        min={0}
                        max={10}
                        defaultValue={10}
                        required
                    ></input>
                </div>
                <div className="grade-menu-input">
                    <label>Date: </label>
                    <input
                        type="date"
                        name="gradeDate"
                        defaultValue={getCurrentDate()}
                        required
                    ></input>
                </div>
                <div className="grade-menu-submit">
                    <input type="submit" value={"Save grade"}></input>
                </div>
            </form>
        </div>
    );

    async function checkNewGradeForm(event) {
        event.preventDefault();
        const gradeValue = event.target.gradeValue.value;
        const gradeDate = event.target.gradeDate.value;

        await axios.post("/grades/create", {
            value: gradeValue,
            date: gradeDate,
            studentId: studentId,
            groupCourseId: id,
        });
        navigate(0);
    }

    function getCurrentDate() {
        return new Date().toISOString().slice(0, 10);
    }
}

async function updateGradeValue(id, value) {
    await axios.put("/grades/updateGradeValue", {
        id: id,
        value: value,
    });
}
