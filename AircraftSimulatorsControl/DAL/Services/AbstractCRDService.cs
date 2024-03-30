using AircraftSimulatorsControl.DAL.Entity;
using Microsoft.EntityFrameworkCore;

namespace AircraftSimulatorsControl.DAL.Services
{
    public abstract class AbstractCRDService<TEntity, TDbContext>
        where TEntity : class, IEntity
        where TDbContext : DbContext
    {
        protected readonly TDbContext _context;

        protected AbstractCRDService(TDbContext context)
        {
            _context = context;
        }

        public virtual async Task<TEntity> GetByIdAsync(int id)
        {
            TEntity? entity = await _context.Set<TEntity>().FindAsync(id);

            if (entity == null)
            {
                throw new InvalidOperationException("Entity with such id wasn't found!");
            }

            return entity;
        }

        public virtual async Task AddAsync(TEntity entity)
        {
            try
            {
                await _context.Set<TEntity>().AddAsync(entity);
                await _context.SaveChangesAsync();
            } catch (Exception ex)
            {
                throw new InvalidOperationException(ex.Message);
            }
            
        }

        public virtual async Task DeleteAsync(int id)
        {
            var entity = await GetByIdAsync(id);
            if (entity != null)
            {
                _context.Set<TEntity>().Remove(entity);
                await _context.SaveChangesAsync();
            } else
            {
                throw new InvalidOperationException("Entity with such id wasn't found! Deleting failed!");
            }
        }

        public virtual IQueryable<TEntity> GetAll()
        {
            return _context.Set<TEntity>().AsQueryable();
        }
    }
}
