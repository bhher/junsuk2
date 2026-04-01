import { motion } from 'motion/react';
import { ExternalLink, Github } from 'lucide-react';
import { PROJECTS } from '../constants';

export default function Portfolio() {
  return (
    <section id="portfolio" className="py-32 px-6">
      <div className="max-w-7xl mx-auto">
        <div className="flex flex-col md:flex-row md:items-end justify-between mb-20 gap-8">
          <div>
            <span className="text-[#f4a261] font-mono text-sm tracking-widest uppercase mb-4 block">Selected Works</span>
            <h2 className="text-5xl md:text-7xl font-bold text-[#2d3436] tracking-tighter">PORTFOLIO</h2>
          </div>
          <p className="text-[#636e72] max-w-md text-lg">
            A collection of projects that showcase my passion for building clean, 
            functional, and user-centric digital products.
          </p>
        </div>

        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">
          {PROJECTS.map((project, index) => (
            <motion.div
              key={project.id}
              initial={{ opacity: 0, y: 20 }}
              whileInView={{ opacity: 1, y: 0 }}
              viewport={{ once: true }}
              transition={{ delay: index * 0.1 }}
              className="group bg-white border border-[#8da9c4]/10 rounded-3xl overflow-hidden hover:border-[#8da9c4]/30 transition-all shadow-sm hover:shadow-xl hover:shadow-[#8da9c4]/5"
            >
              <div className="aspect-video overflow-hidden relative">
                <img 
                  src={project.image} 
                  alt={project.title}
                  className="w-full h-full object-cover transition-transform duration-500 group-hover:scale-110"
                  referrerPolicy="no-referrer"
                />
                <div className="absolute inset-0 bg-[#8da9c4]/40 opacity-0 group-hover:opacity-100 transition-opacity flex items-center justify-center space-x-4">
                  <a 
                    href={project.githubUrl} 
                    target="_blank" 
                    rel="noopener noreferrer"
                    className="p-3 bg-white text-[#2d3436] rounded-full hover:scale-110 transition-transform shadow-lg"
                  >
                    <Github size={20} />
                  </a>
                  {project.liveUrl && (
                    <a 
                      href={project.liveUrl} 
                      target="_blank" 
                      rel="noopener noreferrer"
                      className="p-3 bg-white text-[#2d3436] rounded-full hover:scale-110 transition-transform shadow-lg"
                    >
                      <ExternalLink size={20} />
                    </a>
                  )}
                </div>
              </div>
              
              <div className="p-8">
                <div className="flex flex-wrap gap-2 mb-4">
                  {project.tags.map(tag => (
                    <span key={tag} className="text-[10px] font-mono uppercase tracking-widest text-[#8da9c4] bg-[#8da9c4]/5 px-2 py-1 rounded">
                      {tag}
                    </span>
                  ))}
                </div>
                <h3 className="text-2xl font-bold text-[#2d3436] mb-3 tracking-tight">{project.title}</h3>
                <p className="text-[#636e72] text-sm leading-relaxed line-clamp-2">
                  {project.description}
                </p>
              </div>
            </motion.div>
          ))}
        </div>
      </div>
    </section>
  );
}
