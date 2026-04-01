import { motion } from 'motion/react';
import * as Icons from 'lucide-react';
import { SKILLS, EXPERIENCES, EDUCATION } from '../constants';

export default function About() {
  return (
    <section id="about" className="py-32 px-6 bg-white">
      <div className="max-w-7xl mx-auto">
        <div className="grid lg:grid-cols-2 gap-20">
          {/* Left: Skills */}
          <motion.div
            initial={{ opacity: 0, x: -20 }}
            whileInView={{ opacity: 1, x: 0 }}
            viewport={{ once: true }}
          >
            <h2 className="text-4xl font-bold text-[#2d3436] mb-12 tracking-tighter">TECHNICAL SKILLS</h2>
            <div className="grid grid-cols-2 sm:grid-cols-3 gap-4">
              {SKILLS.map((skill) => {
                const IconComponent = (Icons as any)[skill.icon];
                return (
                  <div 
                    key={skill.name}
                    className="p-6 bg-[#fdfbf7] border border-[#8da9c4]/10 rounded-2xl hover:border-[#8da9c4]/50 transition-colors group"
                  >
                    {IconComponent && <IconComponent className="mb-4 text-[#8da9c4]/60 group-hover:text-[#8da9c4] transition-colors" size={24} />}
                    <p className="text-sm font-medium text-[#636e72]">{skill.name}</p>
                  </div>
                );
              })}
            </div>
          </motion.div>

          {/* Right: Experience & Education */}
          <motion.div
            initial={{ opacity: 0, x: 20 }}
            whileInView={{ opacity: 1, x: 0 }}
            viewport={{ once: true }}
            className="space-y-16"
          >
            <div>
              <h2 className="text-4xl font-bold text-[#2d3436] mb-12 tracking-tighter">EXPERIENCE</h2>
              <div className="space-y-8">
                {EXPERIENCES.map((exp, index) => (
                  <div key={index} className="relative pl-8 border-l border-[#8da9c4]/20">
                    <div className="absolute left-[-5px] top-0 w-[9px] h-[9px] bg-[#8da9c4] rounded-full" />
                    <span className="text-xs font-mono text-[#f4a261] uppercase tracking-widest">{exp.period}</span>
                    <h3 className="text-xl font-bold text-[#2d3436] mt-1">{exp.position}</h3>
                    <p className="text-[#636e72] font-medium mb-3">{exp.company}</p>
                    <p className="text-[#636e72] text-sm leading-relaxed">{exp.description}</p>
                  </div>
                ))}
              </div>
            </div>

            <div>
              <h2 className="text-4xl font-bold text-[#2d3436] mb-12 tracking-tighter">EDUCATION</h2>
              <div className="space-y-8">
                {EDUCATION.map((edu, index) => (
                  <div key={index} className="relative pl-8 border-l border-[#8da9c4]/20">
                    <div className="absolute left-[-5px] top-0 w-[9px] h-[9px] bg-[#8da9c4]/40 rounded-full" />
                    <span className="text-xs font-mono text-[#636e72] uppercase tracking-widest">{edu.period}</span>
                    <h3 className="text-xl font-bold text-[#2d3436] mt-1">{edu.degree}</h3>
                    <p className="text-[#636e72] font-medium">{edu.school}</p>
                  </div>
                ))}
              </div>
            </div>
          </motion.div>
        </div>
      </div>
    </section>
  );
}
