using AircraftSimulatorsControl.DAL.Services;
using Microsoft.AspNetCore.Mvc;

namespace AircraftSimulatorsControl.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class WeatherForecastController : ControllerBase
    {

        private readonly ComponentService _service;


        private static readonly string[] Summaries = new[]
        {
            "Freezing", "Bracing", "Chilly", "Cool", "Mild", "Warm", "Balmy", "Hot", "Sweltering", "Scorching"
        };

        private readonly ILogger<WeatherForecastController> _logger;

        public WeatherForecastController(ILogger<WeatherForecastController> logger, ComponentService service)
        {   _service = service;
            _logger = logger;
        }

        [HttpGet]
        public IEnumerable<WeatherForecast> Get()
        {
            return (IEnumerable<WeatherForecast>) _service.GetAll();
        }
    }
}
