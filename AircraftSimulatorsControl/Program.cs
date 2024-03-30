using AircraftSimulatorsControl.Configuration;
using AircraftSimulatorsControl.DAL;
using AircraftSimulatorsControl.Data;

using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Options;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddControllers(); // Add services to the container.

builder.Services.AddDbContext<ApplicationDbContext>(options =>
    options.UseNpgsql(builder.Configuration.GetConnectionString("DefaultConnection"))
                                           .UseSnakeCaseNamingConvention()); // Add dbContext

DependencyInjectionConfiguration.RegisterServices(builder.Services); // Add all services

builder.Services.AddSingleton<DataSeeder>(); // Add data seeder

var app = builder.Build();

var databaseInitializer = app.Services.GetRequiredService<DataSeeder>();
await databaseInitializer.InitData();

// Configure the HTTP request pipeline.

app.UseHttpsRedirection();

//app.UseAuthorization();

app.MapControllers();

app.Run();
