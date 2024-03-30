namespace AircraftSimulatorsControl.DTO.API
{

    public class TechInfoResponse
    {

        public int Id { get; set; }

        public bool IsTechCheckRequired { get; set; }

        public string Description { get; set; } = "";

        public string LastTechCheckDate { get; set; } = "00.00.0000";


        public class InvalidComponent
        {
            public int Id { get; set; }

            public string Status { get; set; } = "Неизвестная ошибка";
            public string ErrorDescription { get; set; } = "Описание отсутствует";

        }

    }
}
