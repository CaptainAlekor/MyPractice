import { BrowserRouter, Routes, Route } from "react-router-dom";

import GradesTable from "./components/grades-table.component";
import LoginForm from "./components/login.component";
import GroupCoursesList from "./components/professor-group-courses-list.component";

import "./App.css";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="login" element={<LoginForm/>}/>
                <Route path="rating" element={<GradesTable/>}/>
                <Route path="professor-group-courses" element={<GroupCoursesList/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
