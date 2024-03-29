import { Line } from 'react-chartjs-2';
import {CategoryScale, LinearScale, Chart, PointElement, LineElement} from 'chart.js';

// Регистрируем шкалы CategoryScale и LinearScale
Chart.register(CategoryScale, LinearScale, PointElement, LineElement);

export default function LineChart({ values }) {
    const labels = values.map((_, index) => index); // Создаем массив меток от 0 до длины массива значений

    const data = {
        labels: labels,
        datasets: [
            {
                pointBorderColor: 'rgb(48,137,137)',
                pointBackgroundColor: 'rgb(241,241,241)',
                pointHitRadius: 10,
                data: values,
                pointBorderWidth: 1,
                fill: false,
                pointRadius: 1
            }
        ]
    };

    const options = {
        scales: {
            x: {
                type: 'category' // Указываем тип оси X как категориальный
            },
            y: {
                type: 'linear' // Указываем тип оси Y как линейный
            }
        }
    };

    return (
        <Line style={{width: 100}} data={data} options={options} />
    );
}
