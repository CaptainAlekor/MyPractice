import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import GradesTable from "./components/grades-table.component";
import LoginForm from "./components/login.component";
import GroupCoursesList from "./components/professor-group-courses-list.component";

import "./App.css";
import GroupCourseTable from "./components/group-course-table.component";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Navigate to="login" replace={true} />}/>
                <Route path="login" element={<LoginForm/>}/>
                <Route path="rating" element={<GradesTable/>}/>
                <Route path="professor-group-courses" element={<GroupCoursesList/>}/>
                <Route path="professor-group-courses/:id" element={<GroupCourseTable/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
