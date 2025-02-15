import React, { useState, useEffect } from "react";
import "./notifications.scss"; // Styling for notifications (defined below)

// Notification data structure
interface Notification {
    id: number;
    message: string;
    type: "success" | "error" | "info"; // Type of notification
}

const Notifications: React.FC = () => {
    const [notifications, setNotifications] = useState<Notification[]>([]);

    // Add a new notification
    const show = (message: string, type: "success" | "error" | "info" = "info") => {
        const id = Date.now(); // Unique ID for each notification
        setNotifications((prev) => [...prev, { id, message, type }]);

        // Automatically remove notification after 5 seconds
        setTimeout(() => {
            setNotifications((prev) => prev.filter((notification) => notification.id !== id));
        }, 5000);
    };

    // Expose the show function globally (optional, for easy access)
    useEffect(() => {
        (window as any).Notification = { show };
    }, []);

    return (
        // Notification container
        <div className="notifications-container">
            {notifications.map(({ id, message, type }) => (
                <div key={id} className={`notification ${type}`}>
                    {message}
                </div>
            ))}
        </div>
    );
};

export default Notifications;