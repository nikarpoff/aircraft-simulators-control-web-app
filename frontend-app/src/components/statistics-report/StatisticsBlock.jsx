import LineChart from "../UI/chart/LineChart";

export default function StatisticsBlock({ title, valuesArray }) {
    let isStatisticsAvailable = true;

    let last;
    let roundedAverage;
    let min;
    let max;

    if (typeof valuesArray === "undefined" || valuesArray.length === 0) {
        isStatisticsAvailable = false;
    } else {
        last = valuesArray[valuesArray.length - 1];

        const sum = valuesArray.reduce((total, current) => total + current, 0);
        const average = sum / valuesArray.length;
        roundedAverage = average.toFixed(2);

        min = Math.min(...valuesArray);
        max = Math.max(...valuesArray);
    }

    return (
        <div className="analytics-block">
            <p style={{fontWeight: "bold"}}>{title}</p>

            {isStatisticsAvailable &&
                <div>
                    <ul>
                        <li>{`Последнее измерение: ${last}`}</li>
                        <li>{`Среднее: ${roundedAverage}`}</li>
                        <li>{`Минимальное: ${min}`}</li>
                        <li>{`Максимальное: ${max}`}</li>
                    </ul>

                    <LineChart values={valuesArray} />
                </div>
            }

            {!isStatisticsAvailable && <p style={{color: "coral"}}>Статистика недоступна!</p>}
        </div>

    );
}