import { BrowserRouter, Routes, Route } from "react-router-dom";

import GradesTable from "./components/grades-table.component";
import LoginForm from "./components/login.component";

import "./App.css";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="login" element={<LoginForm />} />
                <Route path="rating" element={<GradesTable />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;
