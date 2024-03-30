using AircraftSimulatorsControl.DAL;
using AircraftSimulatorsControl.DAL.Entity;
using AircraftSimulatorsControl.DAL.Services;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Options;

namespace AircraftSimulatorsControl.Data
{
    public class DataSeeder
    {

        private readonly SimulatorService _simulatorService;
        private readonly ComponentService _componentService;

        public DataSeeder(SimulatorService simulatorService, ComponentService componentService, ApplicationDbContext context)
        {
            context.Database.EnsureCreated();

            _simulatorService = simulatorService;
            _componentService = componentService;
        }

        public async Task InitData()
        {
            Simulator[] simulators = new Simulator[3];
            Component[] components = new Component[6];

            simulators[0] = new Simulator()
            {
                Model = "WSIM-32F6",
                Type = "Симулятор военной авиации",
                SimulatorName = "V-76 simulator",
                ProductionDate = new DateOnly(2003, 12, 27),
                CommissioningDate = new DateOnly(2004, 01, 22),
                LastTechCheckDate = new DateOnly(2023, 11, 11),
                TechCheckFrequrency = 12
            };

            simulators[1] = new Simulator()
            {
                Model = "EMU-CIVIL-B78",
                Type = "Симулятор гражданской авиации",
                SimulatorName = "Boing 78 simulator",
                ProductionDate = new DateOnly(2001, 11, 09),
                CommissioningDate = new DateOnly(2002, 04, 21),
                LastTechCheckDate = new DateOnly(2024, 03, 01),
                TechCheckFrequrency = 36
            };

            simulators[2] = new Simulator()
            {
                Model = "ENG0004",
                Type = "Инженерный симулятор",
                SimulatorName = "Hork-22",
                ProductionDate = new DateOnly(2006, 12, 01),
                CommissioningDate = new DateOnly(2006, 12, 21),
                LastTechCheckDate = new DateOnly(2020, 05, 14),
                TechCheckFrequrency = 24
            };


            components[0] = new Component()
            {
                Name = "MUTH0",
                SimulatorId = simulators[0].SimulatorId,
                Simulator = simulators[0]
            };

            components[1] = new Component()
            {
                Name = "MUTL2",
                SimulatorId = simulators[0].SimulatorId,
                Simulator = simulators[0]
            };

            components[2] = new Component()
            {
                Name = "YUA21",
                SimulatorId = simulators[1].SimulatorId,
                Simulator = simulators[1]
            };

            components[3] = new Component()
            {
                Name = "LMO00",
                SimulatorId = simulators[2].SimulatorId,
                Simulator = simulators[2]
            };

            components[4] = new Component()
            {
                Name = "ORH12",
                SimulatorId = simulators[2].SimulatorId,
                Simulator = simulators[2]
            };

            components[5] = new Component()
            {
                Name = "PLL01",
                SimulatorId = simulators[2].SimulatorId,
                Simulator = simulators[2]
            };

            simulators[0].Components = new List<Component> { components[0], components[1] };
            simulators[1].Components = new List<Component> { components[2] };
            simulators[2].Components = new List<Component> { components[3], components[4], components[5] };

            foreach (var simulator in simulators)
            {
                await _simulatorService.AddAsync(simulator);
            }

            foreach (var component in components)
            {
                await _componentService.AddAsync(component);
            }
        }

    }
}
