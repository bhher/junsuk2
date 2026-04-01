import { motion } from 'motion/react';
import { ArrowDownRight, Github, Linkedin, Mail } from 'lucide-react';
import { SOCIAL_LINKS } from '../constants';

export default function Hero() {
  return (
    <section id="home" className="min-h-screen flex flex-col justify-center px-6 pt-20">
      <div className="max-w-7xl mx-auto w-full">
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.8 }}
        >
          <span className="text-[#f4a261] font-mono text-sm tracking-widest uppercase mb-4 block">
            Available for projects
          </span>
          <h1 className="text-6xl md:text-8xl lg:text-9xl font-bold tracking-tighter text-[#2d3436] leading-[0.9] mb-8">
            CREATIVE <br />
            <span className="text-[#8da9c4] italic">DEVELOPER</span>
          </h1>
          
          <div className="grid md:grid-columns-2 gap-12 items-end mt-12">
            <motion.p 
              initial={{ opacity: 0 }}
              animate={{ opacity: 1 }}
              transition={{ delay: 0.5, duration: 1 }}
              className="text-xl text-[#636e72] max-w-xl leading-relaxed"
            >
              I build high-performance, accessible, and visually stunning digital experiences. 
              Specializing in React, TypeScript, and modern web architectures.
            </motion.p>
            
            <div className="flex flex-wrap gap-6 items-center">
              <div className="flex space-x-4">
                <a href={SOCIAL_LINKS.github} target="_blank" rel="noopener noreferrer" className="p-3 rounded-full border border-[#8da9c4]/20 text-[#8da9c4] hover:bg-[#8da9c4] hover:text-white transition-all">
                  <Github size={20} />
                </a>
                <a href={SOCIAL_LINKS.linkedin} target="_blank" rel="noopener noreferrer" className="p-3 rounded-full border border-[#8da9c4]/20 text-[#8da9c4] hover:bg-[#8da9c4] hover:text-white transition-all">
                  <Linkedin size={20} />
                </a>
                <a href={`mailto:${SOCIAL_LINKS.email}`} className="p-3 rounded-full border border-[#8da9c4]/20 text-[#8da9c4] hover:bg-[#8da9c4] hover:text-white transition-all">
                  <Mail size={20} />
                </a>
              </div>
              
              <motion.a
                href="#portfolio"
                whileHover={{ scale: 1.05 }}
                whileTap={{ scale: 0.95 }}
                onClick={(e) => {
                  e.preventDefault();
                  document.querySelector('#portfolio')?.scrollIntoView({ behavior: 'smooth' });
                }}
                className="flex items-center space-x-2 bg-[#8da9c4] text-white px-8 py-4 rounded-full font-bold uppercase tracking-tighter shadow-lg shadow-[#8da9c4]/20"
              >
                <span>View My Work</span>
                <ArrowDownRight size={20} />
              </motion.a>
            </div>
          </div>
        </motion.div>
      </div>
      
      {/* Background Decorative Element */}
      <div className="absolute top-1/2 right-0 -translate-y-1/2 w-1/2 h-1/2 bg-[#8da9c4]/10 blur-[120px] rounded-full -z-10" />
    </section>
  );
}
