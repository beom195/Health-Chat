document.addEventListener('DOMContentLoaded', function () {
    let calendarEl = document.getElementById('calendar');
    let calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        selectable: true,
        events: [],

        dateClick: function (info){
            console.log("Clicked events occurs : date = " + info.dateStr);

        }
    });
    calendar.render();
});