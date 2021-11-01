// school dashboard charts
// school bar data sets
{
    const assessmentsBarLabels = ['First Year S1', 'First Year S2', 'Second Year S1', 'Second Year S2', 'Third Year S1', 'Third Year S2'];
    const assessmentBarData = {
        labels: assessmentsBarLabels,
        datasets: [{
            label: 'Submitted',
            data: [65, 59, 80, 87, 66, 89],
            backgroundColor: 'rgba(6, 126, 237, 0.2)',
            borderColor: 'rgb(6, 126, 237)',
            borderWidth: 1
        },
        {
            label: 'Missed',
            data: [5, 9, 8, 2, 5, 6],
            backgroundColor: 'rgba(255, 124, 31, 0.2)',
            borderColor: 'rgb(255, 124, 31)',
            borderWidth: 1
        }
        ]
    };
    const assessementBarConfig = {
        type: 'bar',
        data: assessmentBarData,
        options: {
            scales: {
                xAxes: [{
                    gridLines: {
                        display: false
                    }
                }],
                y: {
                    beginAtZero: true
                }
            }
        },
    };

    var myAssessmentBarChart = new Chart(
        document.getElementById('assessment-bar-chart'),
        assessementBarConfig
    );
}


// pass rate bar data sets
{

    const passRateLabels = ['First Year S1', 'First Year S2', 'Second Year S1', 'Second Year S2', 'Third Year S1', 'Third Year S2'];
    const passRateData = {
        labels: passRateLabels,
        datasets: [{
            label: 'Passed',
            data: [65, 59, 80, 87, 66, 89],
            backgroundColor: 'rgba(6, 126, 237, 0.2)',
            borderColor: 'rgb(6, 126, 237)',
            borderWidth: 1
        },
        {
            label: 'Failed',
            data: [5, 9, 8, 2, 5, 6],
            backgroundColor: 'rgba(255, 124, 31, 0.2)',
            borderColor: 'rgb(255, 124, 31)',
            borderWidth: 1
        },
        {
            label: 'Passed With Distinctions',
            data: [6, 5, 21, 10, 7, 16],
            backgroundColor: 'rgba(28, 200, 138, 0.2)',
            borderColor: 'rgb(28, 200, 138)',
            borderWidth: 1
        }
        ]
    };

    const passRateConfig = {
        type: 'bar',
        data: passRateData,
        options: {
            scales: {
                xAxes: [{
                    gridLines: {
                        display: false
                    }
                }],
                y: {
                    beginAtZero: true
                }
            }
        },
    };

    var myPassRateBarChart = new Chart(
        document.getElementById('pass-rate-bar-chart'),
        passRateConfig
    );
}

// class results data sets
{
    const cData = {
        labels: [
            'Passed',
            'Failed',
            'Passed with Distinctions'
        ],
        datasets: [{
            label: 'My First Dataset',
            data: [273, 14, 43],
            backgroundColor: [
                'rgb(6, 126, 237)',
                'rgb(255, 124, 31)',
                'rgb(28, 200, 138)'
            ],
            spacing: 10,
            hoverOffset: 4
        }]
    };
    const cConfig = {
        type: 'doughnut',
        data: cData,
    };

    var myDoughnutChart = new Chart(
        document.getElementById('doughnut-chart'),
        cConfig
    );
}

// pass rate line chart
{
    const passRateLineLabels = ['2019 Sem1', '2019 Sem2', '2020 Sem1', '2020 Sem2', '2021 Sem1', '2021 Sem2'];
    const passRateLineData = {
        labels: passRateLineLabels,
        datasets: [
            {
                label: 'Passed',
                data: [165, 187, 180, 167, 178, 198],
                fill: true,
                backgroundColor: 'rgba(6, 126, 237, 0.2)',
                borderColor: 'rgb(6, 126, 237)',
                tension: 0.4
            },
            {
                label: 'Failed',
                data: [79, 68, 72, 56, 78, 89],
                fill: true,
                backgroundColor: 'rgba(255, 124, 31, 0.2)',
                borderColor: 'rgb(255, 124, 31)',
                tension: 0.4
            },
            {
                label: 'Passed With Distinctions',
                data: [71, 78, 92, 88, 77, 89],
                fill: true,
                backgroundColor: 'rgba(28, 200, 138, 0.2)',
                borderColor: 'rgb(28, 200, 138)',
                tension: 0.5
            }
        ]
    };
    const passRateLineConfig = {
        type: 'line',
        data: passRateLineData,
        options: {
            scales: {
                xAxes: [{
                    gridLines: {
                        display: false
                    }
                }],
                y: {
                    beginAtZero: true
                }
            }
        }
    };
    var mypassRateLineChart = new Chart(
        document.getElementById('pass-rate-line-chart'),
        passRateLineConfig
    );
}


// line chart
{
    const lineLabels = ['2019\nSem1', '2019 Sem2', '2020 Sem1', '2020 Sem2', '2021 Sem1', '2021 Sem2'];
    const lineData = {
        labels: lineLabels,
        datasets: [
            {
                label: 'Submitted',
                data: [65, 87, 80, 67, 78, 98],
                fill: true,
                backgroundColor: 'rgba(6, 126, 237, 0.2)',
                borderColor: 'rgb(6, 126, 237)',
                tension: 0.4
            },
            {
                label: 'Missed',
                data: [79, 68, 72, 56, 78, 89],
                fill: true,
                backgroundColor: 'rgba(255, 124, 31, 0.2)',
                borderColor: 'rgb(255, 124, 31)',
                tension: 0.4
            }
        ]
    };
    const lineConfig = {
        type: 'line',
        data: lineData,
        options: {
            scales: {
                xAxes: [{
                    gridLines: {
                        display: false
                    }
                }],
                y: {
                    beginAtZero: true
                }
            }
        }
    };
    var myLineChart = new Chart(
        document.getElementById('line-chart'),
        lineConfig
    );
}

// radar
const radarData = {
    labels: [
        'Semester',
        'Exam',
        'Assignment',
        'CA 2',
        'CA 1'
    ],
    datasets: [{
        label: 'First Year',
        data: [65, 59, 90, 81, 56],
        fill: true,
        backgroundColor: 'rgba(6, 126, 237, 0.2)',
        borderColor: 'rgb(6, 126, 237)',
        pointBackgroundColor: 'rgb(6, 126, 237)',
        pointBorderColor: '#fff',
        pointHoverBackgroundColor: '#fff',
        pointHoverBorderColor: 'rgb(6, 126, 237)'
    }, {
        label: 'Second Year',
        data: [28, 48, 40, 19, 96],
        fill: true,
        backgroundColor: 'rgba(255, 124, 31, 0.2)',
        borderColor: 'rgb(255, 124, 31)',
        pointBackgroundColor: 'rgb(255, 124, 31)',
        pointBorderColor: '#fff',
        pointHoverBackgroundColor: '#fff',
        pointHoverBorderColor: 'rgb(255, 124, 31)'
    }, {
        label: 'Third Year',
        data: [65, 88, 75, 89, 90],
        fill: true,
        backgroundColor: 'rgba(28, 200, 138, 0.2)',
        borderColor: 'rgb(28, 200, 138)',
        pointBackgroundColor: 'rgb(28, 200, 138)',
        pointBorderColor: '#fff',
        pointHoverBackgroundColor: '#fff',
        pointHoverBorderColor: 'rgb(28, 200, 138)'
    }]
};
const radarConfig = {
    type: 'radar',
    data: radarData,
    options: {
        elements: {
            line: {
                borderWidth: 3
            }
        }
    },
};

var myRadarChart = new Chart(
    document.getElementById('radar-chart'),
    radarConfig
);
