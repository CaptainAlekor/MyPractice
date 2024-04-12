import { useNavigate } from "react-router-dom";

export default function GroupCoursesList() {
    return (
        <div className="group-courses-list-container">
            <GroupCourses/>
        </div>
    );
}

function GroupCourses() {
    const user = JSON.parse(localStorage.getItem('user'))

    const children = []
    user.person.groupCourses.forEach((element) => {
        children.push(<GroupCourse key={element.id} id={element.id} course={element.course.name} group={element.group.name}/>)
    })
    
    return children
}

function GroupCourse({ id, course, group }) {
    const navigate = useNavigate();

    return (
        <>
            <div 
                className="group-courses-list-item"
                onClick={() => {
                    navigate(`/professor-group-courses/${id}`, { replace: false });
                }}
            >
                <div className="course-name">{course}</div>
                <div className="course-group">{group}</div>
            </div>
        </>
    );
}
