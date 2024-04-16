import { useNavigate } from "react-router-dom";

export default function Header() {
    const navigate = useNavigate()

    return (
        <div className="header">
            <div className="logout-button" onClick={() => {
                localStorage.removeItem('user')
                navigate('/login', { replace: false })
            }}>Logout</div>
        </div>
    );
}